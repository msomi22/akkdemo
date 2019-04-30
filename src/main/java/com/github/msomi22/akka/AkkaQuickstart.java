package com.github.msomi22.akka;

import java.io.IOException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaQuickstart {
	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("hellodemoakka");
		try {
			final ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");
			final ActorRef peterGreeter = system.actorOf(AkkaDemo.props("Peter", printerActor), "PeterGreeter");
			final ActorRef madhuGreeter = system.actorOf(AkkaDemo.props("Madhu", printerActor), "MadhuGreeter");
			final ActorRef juliusGreeter = system.actorOf(AkkaDemo.props("Julius Gitonga", printerActor), "JuliusGitongaGreeter");

			peterGreeter.tell(new AkkaDemo.WhoToGreet("AkkaPeter"), ActorRef.noSender());
			peterGreeter.tell(new AkkaDemo.Greet(), ActorRef.noSender());

			peterGreeter.tell(new AkkaDemo.WhoToGreet("LightbendPeter"), ActorRef.noSender());
			peterGreeter.tell(new AkkaDemo.Greet(), ActorRef.noSender());

			madhuGreeter.tell(new AkkaDemo.WhoToGreet("JavaMadhu"), ActorRef.noSender());
			madhuGreeter.tell(new AkkaDemo.Greet(), ActorRef.noSender());

			juliusGreeter.tell(new AkkaDemo.WhoToGreet("KcbJulius"), ActorRef.noSender());
			juliusGreeter.tell(new AkkaDemo.Greet(), ActorRef.noSender());

			System.out.println(">>> Press ENTER to exit <<<");
			System.in.read();
		} catch (IOException ioe) {
		} finally {
			system.terminate();
		}
	}
}