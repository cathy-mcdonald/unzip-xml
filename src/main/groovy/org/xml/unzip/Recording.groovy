package org.xml.unzip

import java.text.SimpleDateFormat

class Recording {
	
	// Date format in XML data
	public static df = new SimpleDateFormat('yyyy-MM-dd HH:mm')
	
	String name;
	String channelName;
	String description;
	Date startTime;
	
	Recording(InputStream is) {
		// Maybe use XmlParser if all data is relevant
		def slurper = new XmlSlurper()
		def xmlRoot = slurper.parse(is)
		def simpleTags = xmlRoot.getAt('tag').getAt('SimpleTag')
		for (tag in simpleTags) {
			if (tag.getAt('name').text().equals('TITLE')) {
				name = tag.getAt('value').text()
			}
			if (tag.getAt('name').text().equals('CHANNEL_NAME')) {
				channelName = tag.getAt('value').text()
			}
			if (tag.getAt('name').text().equals('COMMENT')) {
				description = tag.getAt('value').text()
			}
			if (tag.getAt('name').text().equals('STARTTIME')) {
				startTime = df.parse(tag.getAt('value').text())
			}
		}
	}	

}
