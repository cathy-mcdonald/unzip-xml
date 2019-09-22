package org.xml.unzip

import java.util.zip.ZipException

class App {

	static void main(args) {
		if (args.length < 2) {
			println 'Usage: UnzipXml <input file> <output file>'
			println args.length.toString()
			System.exit(2)
		}
		
		def zipFileName = args[0]
		def channelSet = new HashSet()
		def zipFile = new java.util.zip.ZipFile(new File(zipFileName))
		zipFile.entries().each { entry ->
			def recording = new Recording(zipFile.getInputStream(entry))
			println "TV show: ${recording.getName()}"
			println "Channel: ${recording.getChannelName()}"
			channelSet.add(recording.getChannelName())
			println recording.getDescription()
			println "Start Time: ${recording.getStartTime()}"			
		}
		
		File output = new File(args[1])
		def channelList = channelSet.collect{ channel -> '"' + channel.replaceAll('"', '""') + '"' }.sort()
		output.write channelList.join(', ')
		
	}	
}
