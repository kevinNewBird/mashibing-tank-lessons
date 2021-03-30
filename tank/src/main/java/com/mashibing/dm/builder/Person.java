package com.mashibing.dm.builder;

import java.time.Period;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/31 0:33
 * @version: 1.0
 ***********************/
public class Person {

    Weight w;

    private Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "w=" + w +
                '}';
    }

    static class PersonBuilder{
        Person person = new Person();

        public PersonBuilder buildWeight(double w) {
            person.w = new Weight(w);
            return this;
        }

        public Person build() {
            return person;
        }

    }

   static class Weight{
        double w;

        public Weight(double w) {
            this.w = w;
        }

        @Override
        public String toString() {
            return "Weight{" +
                    "w=" + w +
                    '}';
        }
    }
}

