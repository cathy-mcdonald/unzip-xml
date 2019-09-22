package org.xml.unzip

import static org.junit.Assert.*

import org.junit.Test

class RecordingTest {

	@Test
	public void testRecording() {
		def inputXml = '''<?xml version="1.0" encoding="UTF-8"?>
			<tags>
			  <tag>
			    <SimpleTag>
			      <name>TITLE</name>
			      <value>Gardening Australia</value>
			    </SimpleTag>
			    <SimpleTag>
			      <name>COMMENT</name>
			      <value>We remember Peter Cundall's emotional trip to a memorial garden, Jane Edmanson visits an urban meadow, Josh Byrne explores WA's wildflowers, and Sophie Thomson frames pictures of succulents.</value>
			    </SimpleTag>
			    <SimpleTag>
			      <name>GENRE</name>
			      <value>show/game show (general)</value>
			    </SimpleTag>
			    <SimpleTag>
			      <name>CHANNEL_NAME</name>
			      <value>ABC</value>
			    </SimpleTag>
			    <SimpleTag>
			      <name>STARTTIME</name>
			      <value>2019-09-20 19:29</value>
			    </SimpleTag>
			    <SimpleTag>
			      <name>ENDTIME</name>
			      <value>2019-09-20 20:32</value>
			    </SimpleTag>
			  </tag>
			</tags>'''
				  
		def recording = new Recording(new ByteArrayInputStream(inputXml.getBytes()))
		assertEquals('Gardening Australia', recording.getName())
		assertEquals('ABC', recording.getChannelName())
		
		Calendar expectedStartTime = Calendar.getInstance()
		expectedStartTime.setTimeInMillis(0)
		expectedStartTime.set(2019, Calendar.SEPTEMBER, 20, 19, 29)
		
		assertEquals(expectedStartTime.getTime(), recording.getStartTime())
	}

}
