/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package entities;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name=Book.QUERY_BOOK_BY_AUTHOR,
				query = "SELECT b FROM Book b WHERE b.author =:author"),
	
	@NamedQuery(name=Book.QUERY_BOOK_BY_TITLE,
		query = "SELECT b FROM Book b WHERE b.title =:title")

})


public class Book {  
	
	public static final String  QUERY_BOOK_BY_AUTHOR="findByAuthor"; 
	public static final String  QUERY_BOOK_BY_TITLE="findByTitle"; 
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
	private String author; 
    private String description; 
    private String title;
    
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public int getId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + title + '\'' +
                '}';
    }
}

