
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class EnduranceFrontTestSimulation extends Simulation {

	val endpoint = "http://localhost"

	val httpProtocol = http
		.baseUrl(endpoint)
		.inferHtmlResources()
		.disableCaching
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:78.0) Gecko/20100101 Firefox/78.0")

	object Navigate {
		val home 				= exec(http("Home").get("/")).pause(7)
		val publishingOptions 	= exec(http("Publishing options").get("/publishing-options/")).pause(15)
		val adminSettings 		= exec(http("Managing admin settings").get("/admin-settings/")).pause(3)
		val ghostUser 			= exec(http("Ghost user").get("/author/ghost/")).pause(10)
		val appsAndIntegration 	= exec(http("Apps & integrations").get("/apps-integrations/")).pause(10)
		val organisingContent 	= exec(http("Organising your content").get("/organising-content/")).pause(15)
	} 

	// User browsing website without specific goal.
	val lambdaUser = scenario("Simple browsing session").exec(
		Navigate.home,
		Navigate.publishingOptions,
		Navigate.adminSettings,
		Navigate.ghostUser,
		Navigate.appsAndIntegration
	)

	setUp(
		lambdaUser.inject(
			atOnceUsers(10),
			rampUsersPerSec(10) to 300 during (10 minutes) randomized
		)
	).protocols(httpProtocol)
}