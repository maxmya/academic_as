package com.academic.as.demo.services;

import com.academic.as.demo.controllers.web.models.Task;
import com.academic.as.demo.firebase.FirebaseConstants;
import com.academic.as.demo.firebase.FirebaseHelper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.academic.as.demo.firebase.FirebaseConstants.GROUPS_COLLECTION;
import static com.academic.as.demo.firebase.FirebaseConstants.TASKS_COLLECTION;

@Service
public class FirebaseGroupsService {

    @Autowired
    FirebaseHelper firebaseHelper;

    public List<Task> getAllTasks(String groupId) throws ExecutionException, InterruptedException {
        CollectionReference doc = firebaseHelper.db.
                collection(GROUPS_COLLECTION).
                document(groupId).collection(TASKS_COLLECTION);
        List<QueryDocumentSnapshot> documents = doc.get().get().getDocuments();
        List<Task> tasks = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            tasks.add(document.toObject(Task.class));
        }
        return tasks;
    }


    public void postTask(Task task, String groupId) {
        System.out.println("hello !");
        System.out.println(groupId);
        firebaseHelper.db.collection(GROUPS_COLLECTION)
                .document(groupId)
                .collection(TASKS_COLLECTION).add(task);
    }
}
