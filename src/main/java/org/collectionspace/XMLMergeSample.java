/**
 *  This document is a part of the source code and related artifacts
 *  for CollectionSpace, an open source collections management system
 *  for museums and related institutions:

 *  http://www.collectionspace.org
 *  http://wiki.collectionspace.org

 *  Copyright 2009 University of California at Berkeley

 *  Licensed under the Educational Community License (ECL), Version 2.0.
 *  You may not use this file except in compliance with this License.

 *  You may obtain a copy of the ECL 2.0 License at

 *  https://source.collectionspace.org/collection-space/LICENSE.txt

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.collectionspace;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import ch.elca.el4j.services.xmlmerge.Configurer;
import ch.elca.el4j.services.xmlmerge.config.AttributeMergeConfigurer;
import ch.elca.el4j.services.xmlmerge.config.ConfigurableXmlMerge;
import ch.elca.el4j.services.xmlmerge.config.PropertyXPathConfigurer;

import org.apache.commons.io.FileUtils;

/**
 * An example of how to use the XMLMerge library.
 *
 */
public class XMLMergeSample {
		
	public static void main(String[] args) {
		InputStream result = null;
		try {
			Configurer configurer = new
					PropertyXPathConfigurer("matcher.default=ID\n");
							
//							"xpath.1=/record/section\n"
//							+ "matcher.1=ID\n"
//									//
//							+ "xpath.2=/record/section/field\n"
//							+ "matcher.2=ID\n"
//									//
//							+ "xpath.3=/record/section/repeat\n"
//							+ "matcher.3=ID\n"
//									//
//							+ "xpath.4=/record/section/repeat/field\n"
//							+ "matcher.4=ID");

//			Configurer configurer = new AttributeMergeConfigurer();
						
			FileInputStream ins1 = new FileInputStream("./src/main/resources/original.txt");
			FileInputStream ins2 = new FileInputStream("./src/main/resources/patch.txt");
			
			InputStream[] inputStreamArray = {ins1, ins2};
			
			
			result = new ConfigurableXmlMerge(configurer).merge(inputStreamArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Save the merge result.
		 */
		File mergedOutFile = new File("./target/merged.xml");
		try {
			FileUtils.copyInputStreamToFile(result, mergedOutFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
