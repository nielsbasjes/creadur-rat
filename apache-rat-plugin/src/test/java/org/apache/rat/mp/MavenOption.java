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
package org.apache.rat.mp;

import org.apache.commons.cli.Option;
import org.apache.rat.plugin.BaseRatMojo;

import static java.lang.String.format;

/**
 * A wrapper on Option to provide access to Option info with Maven nomenclature and formatting.
 */
public class MavenOption {
    final Option option;
    final String name;

    /**
     * Constructor.
     * @param option The CLI option
     */
    MavenOption(Option option) {
        this.option = option;
        this.name = BaseRatMojo.createName(option.getLongOpt());
    }

    /**
     * Returns the value as an POM xml node.
     * @param value the value
     * @return the pom xml node.
     */
    public String xmlNode(String  value) {
        return format("<%1$s>%2$s</%1$s>%n", name, value==null ? "false" : value);
    }

    /**
     * Returns true if the option should be an attribute.
     * @return {@code true} if the option should be an attribute
     */
    public boolean isAttribute() {
        return (!option.hasArgs());
    }

    /**
     * Returns true if the option should be an element.
     * @return {@code true} if the option should be an element.
     */
    public boolean isElement() {
        return !isAttribute() || option.getType() != String.class;
    }

    /**
     * Gets the simple class name for the data type for this option.
     * Normally "String".
     * @return the simple class name for the type.
     */
    public String getType() {
        return ((Class<?>) option.getType()).getSimpleName();
    }

    /**
     * Determine if true if the enclosed option expects an argument.
     * @return {@code true} if the enclosed option expects at least one argument.
     */
    public boolean hasArg() {
        return option.hasArg();
    }

    /**
     * the key value for the option.
     * @return the key value for the CLI argument map.
     */
    public String keyValue() {
        return "--" + option.getLongOpt();
    }
}
