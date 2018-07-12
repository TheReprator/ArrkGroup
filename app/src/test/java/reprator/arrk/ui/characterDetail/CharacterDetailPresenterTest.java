package reprator.arrk.ui.characterDetail;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import reprator.arrk.utility.dateUtility.DateUtils;

import static org.junit.Assert.*;

public class CharacterDetailPresenterTest {

    @Mock
    CharacterDetailContract.View characterDetailView;

    @Mock
    DateUtils dateUtils;

    CharacterDetailPresenter characterDetailPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        characterDetailPresenter =  new CharacterDetailPresenter(characterDetailView);
    }

    @Test
    public void findCharcterHeight() {
        characterDetailPresenter.findCharcterHeight("172");
        Mockito.verify(characterDetailView, Mockito.times(1)).setHeight(1.72);
    }

    @Test
    public void findCharcterHeighst() {
        characterDetailPresenter.findCharcterHeight("172");
        Mockito.verify(characterDetailView, Mockito.times(0)).setHeight(1.72);
    }

    @Test
    public void findCharacterWeight() {
    }

    @Test
    public void convertStringDateTime() {
    }

}