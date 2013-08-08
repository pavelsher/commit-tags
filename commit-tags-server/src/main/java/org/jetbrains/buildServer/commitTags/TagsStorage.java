package org.jetbrains.buildServer.commitTags;

import jetbrains.buildServer.vcs.VcsModification;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TagsStorage {
  private ReentrantReadWriteLock myLock = new ReentrantReadWriteLock();
  private final Map<Long, String[]> myAllTags = new HashMap<Long, String[]>();

  public void setTags(@NotNull VcsModification mod, List<String> tags) {
    myLock.writeLock().lock();
    try {
      myAllTags.put(mod.getId(), tags.toArray(new String[tags.size()]));
    } finally {
      myLock.writeLock().unlock();
    }
  }

  @NotNull
  public List<String> getTags(@NotNull VcsModification mod) {
    myLock.readLock().lock();
    try {
      String[] res = myAllTags.get(mod.getId());
      if (res == null) return Collections.emptyList();
      return Arrays.asList(res);
    } finally {
      myLock.readLock().unlock();
    }
  }
}
