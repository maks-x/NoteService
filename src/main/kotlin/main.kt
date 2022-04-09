import objects.*

fun main() {
    val ns = NoteService

    ns.add("First")
    ns.createComment(1)
    ns.createComment(1, "message 2")
    ns.createComment(1, "third message")

    ns.add("Second")
    ns.createComment(2, "comment for second note")
    ns.createComment(1, "fifth comment")

    ns.add("Third")
    ns.createComment(3, "third note's comment")

    ns.add("fourth")

    printNotes()
    ns.delete(2)
    ns.edit(3, title = "Edit note", text = "Edited third note")
    ns.deleteComment(6)
    ns.deleteComment(6)
    ns.editComment(1, "edited first comment")
    printNotes()
    ns.restoreComment(6)
    println(ns.getByID(3))
}

fun printNotes() {
    val notes = NoteService.get()
    for (note in notes) {
        println(note)
    }
}