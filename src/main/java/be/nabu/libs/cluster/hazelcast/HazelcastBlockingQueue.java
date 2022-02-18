package be.nabu.libs.cluster.hazelcast;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.hazelcast.collection.IQueue;

//import com.hazelcast.core.IQueue;

import be.nabu.libs.cluster.api.ClusterBlockingQueue;

public class HazelcastBlockingQueue<T> implements ClusterBlockingQueue<T> {

	private IQueue<T> queue;

	public HazelcastBlockingQueue(IQueue<T> queue) {
		this.queue = queue;
	}

	@Override
	public boolean add(T e) {
		return queue.add(e);
	}

	@Override
	public boolean offer(T e) {
		return queue.offer(e);
	}

	@Override
	public void put(T e) throws InterruptedException {
		queue.put(e);
	}

	@Override
	public boolean offer(T e, long timeout, TimeUnit unit) throws InterruptedException {
		return queue.offer(e, timeout, unit);
	}

	@Override
	public T take() throws InterruptedException {
		return queue.take();
	}

	@Override
	public T poll(long timeout, TimeUnit unit) throws InterruptedException {
		return queue.poll(timeout, unit);
	}

	@Override
	public int remainingCapacity() {
		return queue.remainingCapacity();
	}

	@Override
	public boolean remove(Object o) {
		return queue.remove(o);
	}

	@Override
	public boolean contains(Object o) {
		return queue.contains(o);
	}

	@Override
	public int drainTo(Collection<? super T> c) {
		return queue.drainTo(c);
	}

	@Override
	public int drainTo(Collection<? super T> c, int maxElements) {
		return queue.drainTo(c, maxElements);
	}

	@Override
	public T remove() {
		return queue.remove();
	}

	@Override
	public T poll() {
		return queue.poll();
	}

	@Override
	public T element() {
		return queue.element();
	}

	@Override
	public T peek() {
		return queue.peek();
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return queue.iterator();
	}

	@Override
	public Object[] toArray() {
		return queue.toArray();
	}

	@Override
	public <S> S[] toArray(S[] a) {
		return queue.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return queue.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return queue.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return queue.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return queue.retainAll(c);
	}

	@Override
	public void clear() {
		queue.clear();
	}

	@Override
	public void destroy() {
		queue.destroy();
	}

}
