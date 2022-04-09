## Note Service

### Custom implementation

*based on *[notes](https://vk.com/dev/notes)*
***

##### Ignored fields:

1. ***.add:***
    * privacy
    * comment_privacy
    * privacy_view
    * privacy_comment
2. ***.createComment***
    * owner_id
    * reply_to
    * guid
3. ***.deleteComment***
    * owner_id
4. ***.edit***
   * privacy
   * comment_privacy
   * privacy_view
   * privacy_comment
5. ***.editComment***
   * owner_id
6. ***.get***
   * note_ids
   * user_id
   * offset
   * count
   * sort
7. ***.getByID***
   * owner_id
   * need_wiki
8. ***.getComments***
   * owner_id
   * sort
   * offset
   * count
9. ***.restoreComment***
   * owner_id