/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.rat.tools;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.text.WordUtils;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.apache.rat.Report;
import org.apache.rat.tools.CasedString.StringCase;

/**
 * A simple tool to convert CLI options  to Maven and Ant format
 */
public class Naming {

    public Naming() {}

    private static final String INDENT="    ";

    public  static final List<String> mavenFilterList = Arrays.asList("help");

    public  static final List<String> antFilterList = Arrays.asList("help");

    public static Predicate<Option> optionFilter(List<String> filterList) {
        return option -> !filterList.contains(option.getLongOpt());
    }

    /**
     * Creates the documentation.  Writes to the output specified by the -o or --out option.  Defaults to System.out.
     * @param args the arguments.  Try --help for help.
     * @throws IOException on error
     */
    public static void main(String[] args) throws IOException {
        Options options = Report.buildOptions();
        Predicate<Option> mavenFilter = optionFilter(mavenFilterList);
        Predicate<Option> antFilter = optionFilter(antFilterList);
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("nameMap.csv"), CSVFormat.DEFAULT)) {
            printer.printRecord("CLI", "Maven", "Ant", "Description");
            for (Option option : options.getOptions()) {
                if (option.getLongOpt() != null) {
                    CasedString opt = new CasedString(StringCase.Kebab, option.getLongOpt());
                    String mavenCell = mavenFilter.test(option) ? mavenName("", option, opt) : "-- not supported --";
                    String antCell = antFilter.test(option) ? antName("", option, opt) : "-- not supported --";
                    printer.printRecord(opt, mavenCell, antCell, option.getDescription());
                }
            }
        }
    }

    public static String quote(String s) {
        return String.format("\"%s\"", s);
    }


    public static String asLongArg(Option option) {
        return quote("--"+option.getLongOpt());
    }


    public static String mavenName(String indent, Option option, CasedString name) {
        StringBuilder sb = new StringBuilder();
        if (option.isDeprecated()) {
            sb.append(indent).append("@Deprecated").append(System.lineSeparator());
        }
        sb.append(indent).append("@Parameter(property = ")
        .append(quote("rat."+WordUtils.uncapitalize(name.toCase(StringCase.Camel))));
             if (option.isRequired()) {
                sb.append(" required = true");
            }
        sb.append(")").append(System.lineSeparator())
                .append(indent)
                .append("public void ")
        .append(option.hasArgs() ? "add" : "set" )
                .append(WordUtils.capitalize(name.toCase(StringCase.Camel)))
                .append("(")
                .append(option.hasArg() ? "String " : "boolean ")
                .append(WordUtils.uncapitalize(name.toCase(StringCase.Camel)))
                .append(")");
        return sb.toString();
    }

    public static String antName(String indent, Option option, CasedString name) {
        return antName(indent, option, String.class, name);
    }

    public static String antName(String indent, Option option, Class<?> type, CasedString name) {
        StringBuilder sb = new StringBuilder();
        if (option.isDeprecated()) {
            sb.append(indent).append("@Deprecated").append(System.lineSeparator());
        }
        sb.append(indent).append("public void ").append(option.hasArgs() ? "add" : "set" )
                .append(WordUtils.capitalize(name.toCase(StringCase.Camel)))
                .append("(")
                .append(option.hasArg() ? type.getSimpleName() : "boolean").append(" ")
                .append(WordUtils.uncapitalize(name.toCase(StringCase.Camel)))
                .append(")");
        return sb.toString();
    }
}
