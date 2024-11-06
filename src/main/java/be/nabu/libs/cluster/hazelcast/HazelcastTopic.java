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

import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

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
		final String subscription = topic.addMessageListener(new MessageListener<T>() {
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
