/**
 * This class represents a comment left for a sales item on an online sales site.
 * A comment consists of a comment text, a rating, and an author name. Other users
 * can indicate whether the comment was useful or not (called 'upvoting' or 
 * 'downvoting'). The balance between upvotes and downvotes is stored with comments.
 * A negative vote balance means that the comment received more downvotes than upvotes.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011-07-31
 */
public class Comment implements Comparable<Comment>
{
    private String author;
    private String text;
    private Integer rating;
    private Integer votes;

    /**
     * Create a comment with all necessary details. The initial vote balance is 0.
     */
    public Comment(String author, String text, int rating)
    {
        this.author = author;
        this.text = text;
        if (!ratingInvalid(rating)){
            this.rating = rating;
        }else{
            throw new IllegalArgumentException("Rating Invalido");
        }
        votes = 0;
    }

    /**
     * Return the author of this comment.
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * Return the rating of this comment.
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * Return the vote count (balance of up and down-votes).
     */
    public int getVoteCount()
    {
        return votes;
    }
    
    /**
     * Indicate that this comment is useful ('upvote'). This is used when a reader clicks
     * the 'Yes' button after the "Was this comment helpful?" quesion.
     */
    public void upvote()
    {
        votes++;
    }

    /**
     * Indicate that this comment is not useful ('downvote'). This is used when a reader
     * clicks the 'No' button after the "Was this comment helpful?" quesion.
     */
    public void downvote()
    {
        votes--;
    }
    
     /**
     * Check whether the given rating is invalid. Return true if it is invalid.
     * Valid ratings are in the range [1..5].
     * return true si el rango es invalido.
     */
    private boolean ratingInvalid(int rating)
    {
        return rating < 1 || rating > 5;
    }
    
    /**
     * Return the full text and details of the comment, including 
     * the comment text, author and rating.
     */
    public String toString()
    {
        return author + " " + text + " " + rating;
    }
    
    /**
    * Hashcodetechnique taken from
    * Effective Java by Joshua Bloch.
    */
    @Override
    public int hashCode()
    {
        int result= 17;
        result= 37 * result+ author.hashCode();
        result= 37 * result+ text.hashCode();
        result= 37 * result+ rating.hashCode();
        result= 37 * result+ votes.hashCode();
        return result;
    }
    
    /**
     * Compara un comentario con otro segun su puntuacion (rating)
     * return rating comparado
     */
    @Override
    public int compareTo(Comment c)
    {
        return Integer.compare(c.getRating(), rating);
    }
    
}
