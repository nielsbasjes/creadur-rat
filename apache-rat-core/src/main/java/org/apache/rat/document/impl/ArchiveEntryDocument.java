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

package org.apache.rat.document.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.rat.api.Document;
import org.apache.rat.api.MetaData;
import org.apache.rat.api.RatException;

public class ArchiveEntryDocument implements Document {
	
	private byte[] contents;
    private final String name;

    private final MetaData metaData = new MetaData();

	public ArchiveEntryDocument(File file, byte[] contents) throws RatException {
		super();
		name = DocumentImplUtils.toName(file);
		this.contents = contents;
	}
	
	public MetaData getMetaData() {
		return metaData;
	}

	public String getName() {
		return name;
	}

	public InputStream inputStream() throws IOException {
		return new ByteArrayInputStream(contents);
	}

	public boolean isComposite() {
		return DocumentImplUtils.isZipStream(new ByteArrayInputStream(contents));
	}

	public Reader reader() throws IOException {
		return new InputStreamReader(new ByteArrayInputStream(contents));
	}


    /**
     * Representations suitable for logging.
     * @return a <code>String</code> representation 
     * of this object.
     */
    public String toString()
    {
        return "TarEntryDocument ( "
            + "name = " + this.name + " "
            + "metaData = " + this.metaData + " "
            + " )";
    }
}
