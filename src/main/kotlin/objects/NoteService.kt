package objects

import exceptions.*

object NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()

    fun add(title: String = "Title", text: String = "Some text"): Note {
        val note = Note(notes.lastIndex + 2, title, text)
        notes.add(note)
        return note
    }

    fun createComment(noteID: Int, message: String = "some message"): Comment {
        val note = getByID(noteID)
        val comment = Comment(noteID = note.id, id = comments.lastIndex + 2, message = message)
        comments.add(comment)
        return comment
    }

    fun delete(noteID: Int): Boolean {
        try {
            val note = getByID(noteID)
            notes[noteID - 1] = note.copy(isDeleted = true)
            return true
        } catch (exc: NoteDeletedException) {
            println("\n$exc (has already been deleted)\n")
        }
        return false
    }

    fun deleteComment(commentID: Int): Boolean {
        try {
            val comment = getCommentByID(commentID)
            comments[commentID - 1] = comment.copy(isDeleted = true)
            return true
        } catch (exc: CommentDeletedException) {
            println("\n$exc (has already been deleted)\n")
        }
        return false
    }

    fun edit(noteID: Int, title: String, text: String): Note {
        val note = getByID(noteID)
        notes[noteID - 1] = note.copy(title = title, text = text)
        return notes[noteID - 1]
    }

    fun editComment(commentID: Int, message: String): Comment {
        val comment = getCommentByID(commentID)
        comments[commentID - 1] = comment.copy(message = message)
        return comments[commentID - 1]
    }

    fun get(): List<Note> {
        val currentNotes = mutableListOf<Note>()
        for (note in notes) {
            if (!note.isDeleted) currentNotes.add(note)
        }
        return currentNotes
    }

    fun getByID(noteID: Int): Note {
        try {
            val note = notes[noteID - 1]
            when {
                note.isDeleted -> throw NoteDeletedException("Note with id #$noteID was deleted")
                else -> return note
            }
        } catch (e: IndexOutOfBoundsException) {
            throw NoteNotFoundException("Note with id #$noteID not found")
        }
    }

    fun getComments(noteID: Int): List<Comment> {
        val currentComments = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.noteID == noteID && !comment.isDeleted) currentComments.add(comment)
        }
        return currentComments
    }

    fun restoreComment(commentID: Int): Comment {
        val index = commentID - 1
        try {
            getCommentByID(commentID)
            throw CommentNotDeletedException("Comment with id #$commentID was not deleted")
        } catch (ex: CommentDeletedException) {
            comments[index] = comments[index].copy(isDeleted = false)
        }
        return comments[index]
    }

    private fun getCommentByID(commentID: Int): Comment {
        try {
            val comment = comments[commentID - 1]
            when {
                comment.isDeleted -> throw CommentDeletedException("Comment with id #$commentID was deleted")
                else -> return comment
            }
        } catch (e: IndexOutOfBoundsException) {
            throw CommentNotFoundException("Comment with id #$commentID not found")
        }
    }

    fun reset(code: String) {
        when (code) {
            "ACHTUNG!" -> {
                notes.clear()
                comments.clear()
            }
            else -> return
        }
    }

}