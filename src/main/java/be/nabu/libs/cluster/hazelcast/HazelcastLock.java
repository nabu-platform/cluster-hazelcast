/*
* Copyright (C) 2017 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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

//	@Override
//	public void lock(long leaseTime, TimeUnit leaseTimeUnit) {
//		lock.lock(leaseTime, leaseTimeUnit);
//	}

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

//	@Override
//	public boolean tryLock(long timeout, TimeUnit timeoutUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException {
//		return lock.tryLock(timeout, timeoutUnit, leaseTime, leaseTimeUnit);
//	}

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

	@Override
	public boolean isLockedByCurrentThread() {
		return lock.isLockedByCurrentThread();
	}
}
