package be.nabu.libs.cluster.hazelcast;

import com.hazelcast.core.IAtomicLong;

import be.nabu.libs.cluster.api.ClusterAtomicLong;

public class HazelcastAtomicLong implements ClusterAtomicLong {

	private IAtomicLong atomicLong;

	public HazelcastAtomicLong(IAtomicLong atomicLong) {
		this.atomicLong = atomicLong;
	}

	@Override
	public long addAndGet(long delta) {
		return atomicLong.addAndGet(delta);
	}

	@Override
	public boolean compareAndSet(long expect, long update) {
		return atomicLong.compareAndSet(expect, update);
	}

	@Override
	public long decrementAndGet() {
		return atomicLong.decrementAndGet();
	}

	@Override
	public long get() {
		return atomicLong.get();
	}

	@Override
	public long getAndAdd(long delta) {
		return atomicLong.getAndAdd(delta);
	}

	@Override
	public long getAndDecrement() {
		return atomicLong.getAndAdd(-1);
	}

	@Override
	public long getAndIncrement() {
		return atomicLong.getAndIncrement();
	}

	@Override
	public long getAndSet(long newValue) {
		return atomicLong.getAndSet(newValue);
	}

	@Override
	public long incrementAndGet() {
		return atomicLong.incrementAndGet();
	}

	@Override
	public void lazySet(long newValue) {
		atomicLong.setAsync(newValue);		
	}

	@Override
	public void set(long newValue) {
		atomicLong.set(newValue);		
	}

	@Override
	public boolean equals(Object object) {
		return object instanceof HazelcastAtomicLong
				&& ((HazelcastAtomicLong) object).atomicLong.equals(atomicLong);
	}
	
	@Override
	public int hashCode() {
		return atomicLong.hashCode();
	}
	
	@Override
	public String toString() {
		return atomicLong.toString();
	}

	@Override
	public void destroy() {
		atomicLong.destroy();
	}
}
