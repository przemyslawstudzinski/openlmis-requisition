package org.openlmis.requisition.domain;

import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.annotations.Type;
import org.openlmis.util.View;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments", schema = "requisition")
@NoArgsConstructor
public class Comment extends BaseTimestampedEntity {

  private static final String UUID = "pg-uuid";

  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "requisitionId", nullable = false)
  @JsonView(View.BasicInformation.class)
  @Getter
  @Setter
  private Requisition requisition;

  @Getter
  @Setter
  @Type(type = UUID)
  private UUID authorId;

  @JsonView(View.BasicInformation.class)
  @Getter
  @Setter
  private String body;

  public Comment(Requisition requisition) {
    this.requisition = requisition;
  }

  /**
   * Copy values of attributes into new or updated Comment.
   *
   * @param comment Comment with new values.
   */
  public void updateFrom(Comment comment) {
    this.requisition = comment.getRequisition();
    this.authorId = comment.getAuthorId();
    this.body = comment.getBody();
  }

  /**
   * Creates new instance of Comment object based on data from {@link Comment.Importer}
   *
   * @param importer instance of {@link Comment.Importer}
   * @return new instance od Comment
   */

  public static Comment newComment(Importer importer) {
    Comment comment = new Comment();
    comment.setId(importer.getId());
    comment.setAuthorId(importer.getAuthorId());
    comment.setBody(importer.getBody());
    comment.setCreatedDate(importer.getCreatedDate());
    return comment;
  }

  /**
   * Export this object to the specified exporter (DTO).
   *
   * @param exporter exporter to export to
   */
  public void export(Exporter exporter) {
    exporter.setId(id);
    exporter.setAuthorId(authorId);
    exporter.setRequisitionId(requisition.getId());
    exporter.setBody(body);
    exporter.setCreatedDate(getCreatedDate());

  }

  public interface Exporter {
    void setId(UUID id);

    void setAuthorId(UUID authorId);

    void setRequisitionId(UUID requisitionId);

    void setBody(String body);

    void setCreatedDate(LocalDateTime createdDate);
  }

  public interface Importer {
    UUID getId();

    UUID getAuthorId();

    UUID getRequisitionId();

    String getBody();

    LocalDateTime getCreatedDate();
  }
}
