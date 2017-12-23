package be.nabu.libs.cluster.hazelcast;

import java.util.concurrent.TimeUnit;

import com.hazelcast.core.ILock;

import be.nabu.libs.cluster.api.ClusterLock;

public class HazelcastLock implements ClusterLock {

	private ILock lock;

	public HazelcastLock(ILock lock) {
		this.lock = lock;
	}

	@Override
	public void lock() {
		lock.lock();
	}

	@Override
	public void lock(long leaseTime, TimeUnit leaseTimeUnit) {
		lock.lock(leaseTime, leaseTimeUnit);
	}

	@Override
	public void unlock() {
		lock.unlock();
	}

	@Override
	public boolean tryLock() {
		return lock.tryLock();
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit timeoutUnit) throws InterruptedException {
		return lock.tryLock(timeout, timeoutUnit);
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit timeoutUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException {
		return lock.tryLock(timeout, timeoutUnit, leaseTime, leaseTimeUnit);
	}

	@Override
	public boolean isLocked() {
		return lock.isLocked();
	}

	@Override
	public boolean equals(Object object) {
		return object instanceof HazelcastLock
				&& ((HazelcastLock) object).lock.equals(lock);
	}
	
	@Override
	public int hashCode() {
		return lock.hashCode();
	}
	
	@Override
	public String toString() {
		return lock.toString();
	}

	@Override
	public void destroy() {
		lock.destroy();
	}
}
