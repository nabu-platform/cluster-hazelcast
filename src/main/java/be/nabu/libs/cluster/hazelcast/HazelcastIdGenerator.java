package be.nabu.libs.cluster.hazelcast;

import com.hazelcast.core.IdGenerator;

import be.nabu.libs.cluster.api.ClusterIdGenerator;

public class HazelcastIdGenerator implements ClusterIdGenerator {

	private IdGenerator idGenerator;

	public HazelcastIdGenerator(IdGenerator idGenerator) {
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
