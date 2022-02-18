package be.nabu.libs.cluster.hazelcast;

import com.hazelcast.flakeidgen.FlakeIdGenerator;

//import com.hazelcast.core.IdGenerator;

import be.nabu.libs.cluster.api.ClusterIdGenerator;

public class HazelcastIdGenerator implements ClusterIdGenerator {

//	private IdGenerator idGenerator;
	private FlakeIdGenerator idGenerator;

	public HazelcastIdGenerator(FlakeIdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Override
	public long newId() {
		return idGenerator.newId();
	}

	@Override
	public void destroy() {
		idGenerator.destroy();
	}

}
