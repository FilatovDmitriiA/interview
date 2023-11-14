package com.filatov.rooms;

import com.filatov.rooms.strategy.RandomLoadBalancerStrategy;
import com.filatov.rooms.strategy.RoundRobinLoadBalancerStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyApplicationTest {

    @Test
    public void testLoadBalancerEmpty(){
        var lbRoundRob = new LoadBalancerImp<String>(new RoundRobinLoadBalancerStrategy());
        assertThrows(RuntimeException.class, lbRoundRob::next);
        var lbRand = new LoadBalancerImp<String>(new RoundRobinLoadBalancerStrategy());
        assertThrows(RuntimeException.class, lbRand::next);
    }

    @Test
    public void testLoadBalancerRoundRobin(){
        var lb = new LoadBalancerImp<String>(new RoundRobinLoadBalancerStrategy());
        lb.addResource("url1");
        lb.addResource("url2");
        lb.addResource("url3");
        assertEquals("url1",lb.next());
        assertEquals("url2",lb.next());
        lb.addResource("url4");
        assertEquals("url3",lb.next());
        assertEquals("url4",lb.next());
        assertEquals("url1",lb.next());
    }

    @Test
    public void testLoadBalancerRandom(){
        var lb = new LoadBalancerImp<String>(new RandomLoadBalancerStrategy<>());
        lb.addResource("url1");
        lb.addResource("url2");
        lb.addResource("url3");
        assertThat(List.of("url1","url2","url3")).contains(lb.next());
    }

}
