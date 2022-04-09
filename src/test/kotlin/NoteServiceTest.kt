import exceptions.*
import objects.*
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {
    val ns = NoteService

    @Test
    fun add() {
        ns.reset("ACHTUNG!")
        val note = ns.add()
        assertTrue(ns.get().contains(note))
    }

    @Test
    fun createComment_Success() {
        ns.reset("ACHTUNG!")
        ns.add()
        val comment = ns.createComment(1)
        assertTrue(ns.getComments(1).contains(comment))
    }

    @Test
    fun delete_True() {
        ns.reset("ACHTUNG!")
        ns.add()
        assertTrue(ns.delete(1))
    }

    @Test
    fun delete_False() {
        ns.reset("ACHTUNG!")
        ns.add()
        ns.delete(1)
        assertFalse(ns.delete(1))
    }

    @Test
    fun deleteComment_True() {
        ns.reset("ACHTUNG!")
        ns.add()
        ns.createComment(1)
        assertTrue(ns.deleteComment(1))
    }

    @Test
    fun deleteComment_False() {
        ns.reset("ACHTUNG!")
        ns.add()
        ns.createComment(1)
        ns.deleteComment(1)
        assertFalse(ns.deleteComment(1))
    }

    @Test
    fun edit() {
        ns.reset("ACHTUNG!")
        val note = Note(1, "title", "text", false)
        ns.add()
        val edited = ns.edit(1, "title", "text")
        assertEquals(note, edited)
    }

    @Test
    fun editComment() {
        ns.reset("ACHTUNG!")
        ns.add()
        val comment = ns.createComment(1, "mess")
        val edited = ns.editComment(1, "mess")
        assertEquals(comment, edited)
    }

    @Test
    fun get() {
        ns.reset("ACHTUNG!")
        val note = Note(1, "", "")
        ns.add(note.title, note.text)
        val list = mutableListOf(note)

        assertEquals(ns.get(), list)

    }

    @Test
    fun getByID_Passed() {
        ns.reset("ACHTUNG!")
        val note = Note(1, "", "")
        ns.add(note.title, note.text)

        val result = ns.getByID(1)
        assertEquals(result, note)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByID_NotFound() {
        ns.reset("ACHTUNG!")
        ns.add("", "")
        ns.getByID(2)
    }

    @Test(expected = NoteDeletedException::class)
    fun getByID_Deleted() {
        ns.reset("ACHTUNG!")
        ns.add("", "")
        ns.delete(1)
        ns.getByID(1)
    }

    @Test
    fun getComments_Passed() {
        ns.reset("ACHTUNG!")
        ns.add()
        ns.createComment(1)
        val list = mutableListOf(ns.createComment(1))
        ns.deleteComment(1)
        val comments = ns.getComments(1)
        assertEquals(list, comments)
    }

//    @Test(expected = CommentNotFoundException::class)
//    fun getComments_NotFound() {
//        ns.reset("ACHTUNG!")
//        ns.add()
//        val comments = ns.getComments(1)
//    }
//
//    @Test(expected = CommentDeletedException::class)
//    fun getComments_Deleted() {
//        ns.reset("ACHTUNG!")
//    }

    @Test
    fun restoreComment_Success() {
        ns.reset("ACHTUNG!")
        ns.add()
        val comment = ns.createComment(1, "2")
        ns.deleteComment(1)
        val restored = ns.restoreComment(1)

        assertEquals(comment, restored)
    }

    @Test(expected = CommentNotDeletedException::class)
    fun restoreComment_NotDeleted() {
        ns.reset("ACHTUNG!")
        ns.add()
        ns.createComment(1, "2")
        ns.restoreComment(1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreComment_NotFound() {
        ns.reset("ACHTUNG!")
        ns.add()
        ns.createComment(1, "2")
        ns.restoreComment(2)
    }
}