/*
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 */
package org.apache.rat.analysis.license;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class GeneratedLicenseTest extends AbstractLicenseTest {
    private static String id = "GEN";
    private static String name = "Generated Files";
    private static String notes = "Files that are autmoatically generated.";
    private static String[][] targets = { { "Cayenne", "generated by Cayenne" }, { "JJTree", "Generated By:JJTree" },
            { "JavaCC", "Generated By:JavaCC" }, { "AUTOMATIC", "THIS FILE IS AUTOMATICALLY GENERATED" },
            { "XBeans", "NOTE: this file is autogenerated by XBeans" },
            { "automatic", "This file was automatically generated by" },
            { "do not edit", "# WARNING: DO NOT EDIT OR DELETE THIS WORKSPACE FILE!" },
            { "NMAKE", "# Microsoft Developer Studio Generated NMAKE File" },
            { "MDS Build", "# Microsoft Developer Studio Generated Build File" },
            { "autoheader", "Generated from configure.ac by autoheader" },
            { "aclocal", "generated automatically by aclocal" },
            { "maven project", "build.xml generated by maven from project.xml" },
            { "generated", "This file was generated by" }, { "auto", "This file has been automatically generated." },
            { "do not modify", "Automatically generated - do not modify!" }, { "javadoc style", "Javadoc style sheet" },
            { "SOURCE", "SOURCE FILE GENERATATED" }, { "Batik", "Generated by the Batik" },
            { "file autogenerated", "this file is autogenerated" },
            { "class autogenerated", "This class was autogenerated" }, { "maven", "Generated by Maven" },
            { "Thrift", "Autogenerated by Thrift" },
            { "machine generated", "DO NOT EDIT THIS FILE - it is machine generated" },
            { "generated by", "This class was generated by" }, { "javadoc", "Generated by javadoc" } };

    public static Stream<Arguments> parameterProvider() {
        return Stream.of(Arguments.of( id, id, name, notes, targets));
    }
}
