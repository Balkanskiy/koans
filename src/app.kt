fun main(args: Array<String>) {
    fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
        if (client == null || message == null) return
        var email = client?.personalInfo?.email

        email?.let{
            mailer.sendMessage(email, message)
        }

    }
}

class Client (val personalInfo: PersonalInfo?)
class PersonalInfo (val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}



