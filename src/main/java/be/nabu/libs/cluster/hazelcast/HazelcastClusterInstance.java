package be.nabu.libs.cluster.hazelcast;

import be.nabu.libs.cluster.api.ClusterAtomicLong;
import be.nabu.libs.cluster.api.ClusterBlockingQueue;
import be.nabu.libs.cluster.api.ClusterCountDownLatch;
import be.nabu.libs.cluster.api.ClusterIdGenerator;
import be.nabu.libs.cluster.api.ClusterInstance;
import be.nabu.libs.cluster.api.ClusterList;
import be.nabu.libs.cluster.api.ClusterLock;
import be.nabu.libs.cluster.api.ClusterMap;
import be.nabu.libs.cluster.api.ClusterMember;
import be.nabu.libs.cluster.api.ClusterMembershipListener;
import be.nabu.libs.cluster.api.ClusterMembershipSubscription;
import be.nabu.libs.cluster.api.ClusterSet;
import be.nabu.libs.cluster.api.ClusterTopic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;

public class HazelcastClusterInstance implements ClusterInstance {

	private HazelcastInstance instance;
	private List<ClusterMember> members;
	private List<ClusterMembershipListener> listeners = new ArrayList<ClusterMembershipListener>();

	public HazelcastClusterInstance(HazelcastInstance instance) {
		this.instance = instance;
		members = initializeMembers();
	}
	
	@Override
	public ClusterLock lock(String name) {
		return new HazelcastLock(instance.getLock(name));
	}

	public HazelcastInstance getHazelcastInstance() {
		return instance;
	}

	@Override
	public ClusterAtomicLong atomicLong(String name) {
		return new HazelcastAtomicLong(instance.getAtomicLong(name));
	}

	@Override
	public ClusterCountDownLatch countDownLatch(String name) {
		return new HazelcastCountDownLatch(instance.getCountDownLatch(name));
	}

	@Override
	public ClusterIdGenerator idGenerator(String name) {
		// should probably use the Reliable IdGenerator instead
		// http://docs.hazelcast.org/docs/latest-development/manual/html/Distributed_Data_Structures/Reliable_IdGenerator.html
		return new HazelcastIdGenerator(instance.getIdGenerator(name));
	}

	@Override
	public <K, V> ClusterMap<K, V> map(String name) {
		// more information on all the map-based configuration possible:
		// http://docs.hazelcast.org/docs/latest-development/manual/html/Distributed_Data_Structures/Map/Getting_Map_and_Putting_Entries.html
		return new HazelcastMap<K, V>(instance.getMap(name));
	}

	@Override
	public <T> ClusterBlockingQueue<T> queue(String name) {
		return new HazelcastBlockingQueue<T>(instance.getQueue(name));
	}

	@Override
	public <T> ClusterSet<T> set(String name) {
		return new HazelcastSet<T>(instance.getSet(name));
	}

	@Override
	public <T> ClusterList<T> list(String name) {
		return new HazelcastList<T>(instance.getList(name));
	}

	@Override
	public <T> ClusterTopic<T> topic(String name) {
		return new HazelcastTopic<T>(instance.getTopic(name));
	}

	@Override
	public List<ClusterMember> members() {
		return new ArrayList<ClusterMember>(members);
	}

	private List<ClusterMember> initializeMembers() {
		List<ClusterMember> members = new ArrayList<ClusterMember>();
		for (Member member : instance.getCluster().getMembers()) {
			members.add(new HazelcastMember(member));
		}
		instance.getCluster().addMembershipListener(new MembershipListener() {
			@Override
			public void memberRemoved(MembershipEvent arg0) {
				Iterator<ClusterMember> iterator = members.iterator();
				while (iterator.hasNext()) {
					HazelcastMember member = (HazelcastMember) iterator.next();
					if (member.getMember().equals(arg0.getMember())) {
						if (!listeners.isEmpty()) {
							synchronized(listeners) {
								for (ClusterMembershipListener listener : listeners) {
									try {
										listener.memberRemoved(member);
									}
									catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
						iterator.remove();
					}
				}
			}
			@Override
			public void memberAttributeChanged(MemberAttributeEvent arg0) {
				// do nothing
			}
			@Override
			public void memberAdded(MembershipEvent arg0) {
				HazelcastMember hazelcastMember = new HazelcastMember(arg0.getMember());
				if (!listeners.isEmpty()) {
					synchronized(listeners) {
						for (ClusterMembershipListener listener : listeners) {
							try {
								listener.memberAdded(hazelcastMember);
							}
							catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				members.add(hazelcastMember);
			}
		});
		return members;
	}

	@Override
	public ClusterMembershipSubscription addMembershipListener(ClusterMembershipListener listener) {
		synchronized(listeners) {
			listeners.add(listener);
		}
		return new ClusterMembershipSubscription() {
			@Override
			public void unsubscribe() {
				synchronized(listeners) {
					listeners.remove(listener);
				}
			}
		};
	}
}
