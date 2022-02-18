package be.nabu.libs.cluster.hazelcast;

import java.net.InetAddress;

import com.hazelcast.cluster.Member;

//import com.hazelcast.core.Member;

import be.nabu.libs.cluster.api.ClusterMember;

public class HazelcastMember implements ClusterMember {

	private Member member;

	public HazelcastMember(Member member) {
		this.member = member;
	}

	@Override
	public String getName() {
//		return member.getStringAttribute("name");
		return member.getAttribute("name");
	}

	@Override
	public String getGroup() {
//		return member.getStringAttribute("group");
		return member.getAttribute("group");
	}

	@Override
	public InetAddress getAddress() {
		return member.getSocketAddress().getAddress();
	}

	Member getMember() {
		return member;
	}

}
