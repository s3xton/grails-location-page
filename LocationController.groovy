import org.grails.plugin.geoip.GeoIpService
class LocationController {
	
	GeoIpService geoIpService
	
	def index() { 
		def ipAddress = "8.8.8.8"//getIpAddress()
		def location = geoIpService.getLocation(ipAddress)
		render location.countryName + " " + location.city
	}
	
	def getIpAddress(){
		def ipAddress = request.getRemoteAddr()
		
		if(ipAddress && InetAddressValidator.VALIDATOR.isValid(ipAddress)){
			log.debug("Remote IP Address ::: " + ipAddress)
			
			return ipAddress
		}
		
		ipAddress = request.getHeader("X-Forwarded-For")
		
		if(ipAddress && InetAddressValidator.VALIDATOR.isValid(ipAddress)) {
			log.debug("Remote IP Address ::: " + ipAddress)
			
			return ipAddress
		}
		
		ipAddress = request.getHeader("Client-IP")
		
		if(ipAddress && InetAddressValidator.VALIDATOR.isValid(ipAddress)) {
			log.debug("Remote IP Address ::: " + ipAddress)
			
			return ipAddress
		}
		
		ipAddress
	}
	
	
}
