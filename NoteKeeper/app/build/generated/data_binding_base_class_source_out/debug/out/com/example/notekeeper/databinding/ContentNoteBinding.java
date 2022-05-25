// Generated by view binder compiler. Do not edit!
package com.example.notekeeper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.notekeeper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContentNoteBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Spinner spinnerCourses;

  @NonNull
  public final EditText textNoteText;

  @NonNull
  public final EditText textNoteTitle;

  private ContentNoteBinding(@NonNull ConstraintLayout rootView, @NonNull Spinner spinnerCourses,
      @NonNull EditText textNoteText, @NonNull EditText textNoteTitle) {
    this.rootView = rootView;
    this.spinnerCourses = spinnerCourses;
    this.textNoteText = textNoteText;
    this.textNoteTitle = textNoteTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentNoteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentNoteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_note, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentNoteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.spinner_courses;
      Spinner spinnerCourses = ViewBindings.findChildViewById(rootView, id);
      if (spinnerCourses == null) {
        break missingId;
      }

      id = R.id.text_note_text;
      EditText textNoteText = ViewBindings.findChildViewById(rootView, id);
      if (textNoteText == null) {
        break missingId;
      }

      id = R.id.text_note_title;
      EditText textNoteTitle = ViewBindings.findChildViewById(rootView, id);
      if (textNoteTitle == null) {
        break missingId;
      }

      return new ContentNoteBinding((ConstraintLayout) rootView, spinnerCourses, textNoteText,
          textNoteTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
