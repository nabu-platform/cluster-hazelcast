package be.nabu.libs.cluster.hazelcast;

import java.util.concurrent.TimeUnit;

import com.hazelcast.core.ICountDownLatch;

import be.nabu.libs.cluster.api.ClusterCountDownLatch;

public class HazelcastCountDownLatch implements ClusterCountDownLatch {

	private ICountDownLatch countDownLatch;

	public HazelcastCountDownLatch(ICountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void await() throws InterruptedException {
		countDownLatch.await(365, TimeUnit.DAYS);
	}

	@Override
	public boolean await(long timeout, TimeUnit timeUnit) throws InterruptedException {
		return countDownLatch.await(timeout, timeUnit);
	}

	@Override
	public void countDown() {
		countDownLatch.countDown();
	}

	@Override
	public long getCount() {
		return countDownLatch.getCount();
	}

	@Override
	public boolean trySetCount(int count) {
		return countDownLatch.trySetCount(count);
	}

	@Override
	public void destroy() {
		countDownLatch.destroy();
	}

}
