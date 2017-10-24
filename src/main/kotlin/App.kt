import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.routing.Routing
import route.auth.AuthRouting
import route.user.UserRouting
import tools.Injector

fun Application.main() {

    Injector.configure()

    install(DefaultHeaders)
    install(CallLogging)

    install(Routing) {

        AuthRouting.configure(route = this)
        UserRouting.configure(route = this)

    }

}