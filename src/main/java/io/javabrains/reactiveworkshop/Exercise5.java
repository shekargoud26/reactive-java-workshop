package io.javabrains.reactiveworkshop;

import java.io.IOException;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()
        // ReactiveSources.intNumberMono().subscribe(num -> System.out.println(num));
        // Subscribe to a flux using the error and completion hooks
        // ReactiveSources.intNumbersFluxWithException().subscribe(num -> System.out.println(num), err -> System.out.println(err),
        //         () -> System.out.println("completed"));

        // Subscribe to a flux using an implementation of BaseSubscriber
        // TODO: Write code here
        ReactiveSources.intNumbersFlux().subscribe(new ApnaSubsciber<>());
        System.out.println("Press a key to end");
        System.in.read();
    }

}

// We can implement BaseSubscriber to control backpressure. 
// In this method we define how many items we can handle at a time
// and the flux will provide us with that many items when they are ready.
// 
class ApnaSubsciber<T>  extends BaseSubscriber<T>  {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscription complete: " + subscription);
        request(2);
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.out.println(throwable);
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Completeed");
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println("value: " + value.toString());
        request(2);
    }

    
}