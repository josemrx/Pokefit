    package com.example.josea.pokefit.modelos;

import java.util.ArrayList;
import java.util.List;


    public class PokemonFeed {

        private Integer count;
        private Object previous;
        private List<Result> results = new ArrayList<Result>();
        private String next;

        /**
         *
         * @return
         * The count
         */
        public Integer getCount() {
            return count;
        }

        /**
         *
         * @param count
         * The count
         */
        public void setCount(Integer count) {
            this.count = count;
        }

        /**
         *
         * @return
         * The previous
         */
        public Object getPrevious() {
            return previous;
        }

        /**
         *
         * @param previous
         * The previous
         */
        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        /**
         *
         * @return
         * The results
         */
        public List<Result> getResults() {
            return results;
        }

        /**
         *
         * @param results
         * The results
         */
        public void setResults(List<Result> results) {
            this.results = results;
        }

        /**
         *
         * @return
         * The next
         */
        public String getNext() {
            return next;
        }

        /**
         *
         * @param next
         * The next
         */
        public void setNext(String next) {
            this.next = next;
        }

    }
