package com.codingshuttle.alice.bakery;

import org.springframework.stereotype.Service;

@Service
public class CakeBaker {

    final private Frosting frosting;
    final private Syrup syrup;

    public CakeBaker(Frosting frosting, Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Here is your Cake with "
                + frosting.getFrostingType() + " and " + syrup.getSyrupType());
    }
}
