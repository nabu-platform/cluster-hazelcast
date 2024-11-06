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
