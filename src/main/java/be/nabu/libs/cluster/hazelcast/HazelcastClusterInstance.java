package be.nabu.libs.cluster.hazelcast;

import com.hazelcast.core.HazelcastInstance;

import be.nabu.libs.cluster.api.ClusterAtomicLong;
import be.nabu.libs.cluster.api.ClusterCountDownLatch;
import be.nabu.libs.cluster.api.ClusterInstance;
import be.nabu.libs.cluster.api.ClusterLock;

public class HazelcastClusterInstance implements ClusterInstance {

	private HazelcastInstance instance;

	public HazelcastClusterInstance(HazelcastInstance instance) {
		this.instance = instance;
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
}
