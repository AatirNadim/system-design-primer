// ensure SOLID and use important design patterns wherever necessary

package stack_overflow;
// ****************** USER DETAILS ******************


import java.util.Date;
import java.util.List;

class PersonalDetails {
    String name;
    int age;
    String emailId;
    String uid;
    int upVotes;
}

class UserProfile {
    PersonalDetails userDetails;
    List<QuestionNode> questionsAsked;
    List<AnswerNode> answers;
    List<CommentNode> comments;
}

// ****************** QUESTION ******************
/**
* there also comes the question of some information as to the usage of tags, in terms of count, timeframe and user-wise
* we can leave it be for now and address it later, as a follow-up to the initial design
*/

class Tag {
    String title;
    String netaData;
    String color;
}

abstract class ContentNode {
    String content;
    Date creationTime;
    PersonalDetails userDetails;
    String uid;
}

/**
 * comments should have some way to reference the answers they were added to, when isolated
 */

class CommentNode extends ContentNode {

}

/**
 * answers should have some way to reference the respective questions, when isolated
 */

class AnswerNode extends ContentNode {
    List<CommentNode> commentNodes;
}

class QuestionNode extends ContentNode {
    List<Tag> tags;
    String acceptedAnswerId;
}