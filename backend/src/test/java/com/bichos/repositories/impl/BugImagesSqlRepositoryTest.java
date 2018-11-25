package com.bichos.repositories.impl;

import org.junit.Before;

import com.bichos.utils.SqlClientMock;

public class BugImagesSqlRepositoryTest {

  private BugImagesSqlRepository imagesRepository;

  private SqlClientMock client;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    imagesRepository = new BugImagesSqlRepository(client);
  }

}
