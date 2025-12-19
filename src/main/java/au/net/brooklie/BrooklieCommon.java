/*
 *   Copyright (c) 2020 Bryn E. Price, Kylie A. Price, Oscar E. Price & Oliver B. Price (Brooklie)
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses
 */
package au.net.brooklie;
/* Class imports */
import java.time.LocalDateTime;

/**
 * Common class<br>
 * <br>
 * @author bryn.e.price@gmail.com
 */
public class BrooklieCommon {
    /*-Brooklie-201212T2218-*/
    /* Class variables */
    /* Date components */
    public static final String DATE_COMPONENT = "DATE";
    public static final String TIME_COMPONENT = "TIME";
    /* Log level */
    public static final String LOG_LEVEL_INF = "INF";
    public static final String LOG_LEVEL_BRK = "BRK";
    public static final String LOG_LEVEL_ERR = "ERR";
    public static final String LOG_LEVEL_EXC = "EXC";
    public static final String LOG_LEVEL_SQL = "SQL";
    /* Method Points -*/
    public static final String LOG_METHOD_START = "M>>ST";
    public static final String LOG_METHOD_INTERNAL = "M->IN";
    public static final String LOG_METHOD_FINISH = "M<<FI";
    public static final String LOG_METHOD_NOT_SPECIFIED = "M..XX";
    /**
     * Prints formatted exception text to the system.out stream.
     *
     * @param _objException Exception object.
     */
    public static void log(
            Exception _objException) {
        /*-Brooklie-20220611-*/
        log(LOG_LEVEL_EXC, LOG_METHOD_INTERNAL, "*** Start of exception stack dump ***");
        log(LOG_LEVEL_EXC, LOG_METHOD_INTERNAL, _objException.toString());
        StackTraceElement[] steElements = _objException.getStackTrace();
        StackTraceElement steElement = null;
        for (int mintCount = 0; mintCount < (steElements.length - 1); ++mintCount) {
            steElement = steElements[mintCount];
            StringBuilder strExceptionString = new StringBuilder();
            log(LOG_LEVEL_EXC, LOG_METHOD_INTERNAL,
                    strExceptionString.append(
                                    steElement.getClassName())
                            .append(".")
                            .append(steElement.getMethodName())
                            .append("(")
                            .append(steElement.getFileName())
                            .append(":")
                            .append(steElement.getLineNumber())
                            .append(")").toString());
        }
        log(LOG_LEVEL_EXC, LOG_METHOD_INTERNAL, "*** End of exception stack dump ***");
    }

    /**
     * Prints formatted text to the system.out stream<br>
     *<br>
     * @param _strLogLevel Log level (i.e., 'INF', 'ERR' etc.)
     * @param _strText String to print
     */
    public static void log(
            String _strLogLevel,
            String _strText) {
        /*-Brooklie-20250503-*/
        log(_strLogLevel, LOG_METHOD_NOT_SPECIFIED, _strText);
    }

    /**
     * Prints formatted text to the system.out stream<br>
     *<br>
     * @param _strLogLevel Log level (i.e., 'INF', 'ERR' etc.)
     * @param _strMethodPoint Point in method log event
     * @param _strText String to print
     */
    public static void log(
            String _strLogLevel,
            String _strMethodPoint,
            String _strText) {
        /*-Brooklie-20250503-*/
        int intIndent = -1;
        // Calculate the log text indent
        switch (_strMethodPoint) {
            case LOG_METHOD_START -> intIndent = 0;
            case LOG_METHOD_INTERNAL -> intIndent = 4;
            case LOG_METHOD_FINISH -> intIndent = 0;
            //case LOG_METHOD_NOT_SPECIFIED -> intIndent = 0;
            default -> intIndent = 80;
        }
        System.out.println(
                getDateTime() +
                        ":INP:" +
                        _strLogLevel +
                        ":" +
                        _strMethodPoint +
                        ":" +
                        "-".repeat(intIndent) +
                        _strText);
    }

    /**
     * getDateTime method<br>
     * <br>
     * @return formatted date/time string
     */
    public static String getDateTime() {
        /*-Brooklie-20251213T2253-*/
        // The substring method removes trailing 0's from the time component
        // of the DTS string.
        // The max value should be set to a value that ensures that the substring
        // length (i.e., 24) is <= to the min length of the DTS string
        // returned by the now() method.
        return LocalDateTime.now().toString().substring(0, 24);
    }

    /**
     * Database sub-class<br>
     * <br>
     * @author bryn.e.price@gmail.com
     */
    static class Database {
        /*-Brooklie-20251217T2126-*/
        private static final String url = "jdbc:mariadb://localhost:3306/tsm_test_09";
        private static final String user = "tsmadmin";
        private static final String password = "ei1kyrb";

        /**
         *
         * @return
         */
        public static String getUrl() {
            return url;
        }

        /**
         *
         * @return
         */
        public static String getUser() {
            return user;
        }

        /**
         *
         * @return
         */
        public static String getPassword() {
            return password;
        }

    }
} /* End of class */
