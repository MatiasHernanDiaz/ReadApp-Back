package ar.edu.unsam.algo3

interface EnviaMail {
    fun envioMail(mail: Mail)
}

data class Mail(val from: String, val to: String, val subject: String, val content: String)