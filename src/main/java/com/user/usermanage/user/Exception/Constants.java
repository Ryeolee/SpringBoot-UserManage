package com.user.usermanage.user.Exception;

public class Constants {

    public enum ExceptionClass {

       AUTH("auth"),
       VERIFY("verify"),
       CHANGE("change");
        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }

    }

}
