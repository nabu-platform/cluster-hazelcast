package be.nabu.libs.cluster.hazelcast;

import java.util.UUID;

import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;

//import com.hazelcast.core.ITopic;
//import com.hazelcast.core.Message;
//import com.hazelcast.core.MessageListener;

import be.nabu.libs.cluster.api.ClusterMessageListener;
import be.nabu.libs.cluster.api.ClusterSubscription;
import be.nabu.libs.cluster.api.ClusterTopic;

public class HazelcastTopic<T> implements ClusterTopic<T> {

	private ITopic<T> topic;

	public HazelcastTopic(ITopic<T> topic) {
		this.topic = topic;
	}

	@Override
	public void destroy() {
		topic.destroy();
	}

	@Override
	public void publish(T message) {
		topic.publish(message);
	}

	@Override
	public ClusterSubscription subscribe(final ClusterMessageListener<T> listener) {
		final UUID subscription = topic.addMessageListener(new MessageListener<T>() {
			@Override
			public void onMessage(Message<T> message) {
				listener.onMessage(message.getMessageObject());
			}
		});
		return new ClusterSubscription() {
			@Override
			public void unsubscribe() {
				topic.removeMessageListener(subscription);
			}
		};
	}

}
