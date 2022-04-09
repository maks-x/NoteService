package objects

data class Comment(
    val noteID: Int,
    val id: Int,
    private val message: String,
    val isDeleted: Boolean = false
) {
    override fun toString(): String {
        return "    (ID: $id) --- $message"
    }
}
