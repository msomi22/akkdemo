package com.github.msomi22.akka;

import com.github.msomi22.akka.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class AkkaDemo extends AbstractActor  {

	static public Props props(String message, ActorRef printerActor) {
		return Props.create(AkkaDemo.class, () -> new AkkaDemo(message, printerActor));
	}

	static public class WhoToGreet {
		public final String who;

		public WhoToGreet(String who) {
			this.who = who;
		}
	}

	static public class Greet {
		public Greet() {
		}
	}

	private final String message;
	private final ActorRef printerActor;
	private String greeting = "";

	public AkkaDemo(String message, ActorRef printerActor) {
		this.message = message;
		this.printerActor = printerActor;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(WhoToGreet.class, wtg -> {
					this.greeting = message + ", " + wtg.who;
				})
				.match(Greet.class, x -> {
					printerActor.tell(new Greeting(greeting), getSelf());
				})
				.build();
	}



}
