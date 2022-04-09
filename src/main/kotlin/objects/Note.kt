package objects

data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val isDeleted: Boolean = false
) {
    override fun toString(): String {
        val comments = NoteService.getComments(id).joinToString("\n")
        return """
           |***********
           |Note id: $id
           |Title: $title
           |Text: $text
           |Comments: 
           |$comments
        """.trimMargin()
    }
}
