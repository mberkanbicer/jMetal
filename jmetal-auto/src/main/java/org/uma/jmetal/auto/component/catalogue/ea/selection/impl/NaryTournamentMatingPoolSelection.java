package org.uma.jmetal.auto.component.catalogue.ea.selection.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.uma.jmetal.auto.component.catalogue.ea.selection.MatingPoolSelection;
import org.uma.jmetal.auto.component.util.RankingAndDensityEstimatorPreference;
import org.uma.jmetal.operator.selection.impl.NaryTournamentSelection;
import org.uma.jmetal.solution.Solution;

public class NaryTournamentMatingPoolSelection<S extends Solution<?>>
    implements MatingPoolSelection<S> {
  private NaryTournamentSelection<S> selectionOperator;
  private int matingPoolSize;
  private RankingAndDensityEstimatorPreference<S> preference;

  public NaryTournamentMatingPoolSelection(NaryTournamentSelection<S> selection, int matingPoolSize) {
    this.matingPoolSize = matingPoolSize ;
    this.selectionOperator = selection ;
  }

  public NaryTournamentMatingPoolSelection(
      int tournamentSize, int matingPoolSize, Comparator<S> comparator) {
    selectionOperator = new NaryTournamentSelection<>(tournamentSize, comparator);
    this.matingPoolSize = matingPoolSize;
    preference = null ;
  }

  public NaryTournamentMatingPoolSelection(
      int tournamentSize, int matingPoolSize, RankingAndDensityEstimatorPreference<S> preference) {
    this.preference = preference ;

    this.selectionOperator = new NaryTournamentSelection<>(tournamentSize, preference.getComparator());
    this.matingPoolSize = matingPoolSize;
  }

  public List<S> select(List<S> solutionList) {
    if (null != preference) {
      preference.recompute(solutionList) ;
    }
    List<S> matingPool = new ArrayList<>(matingPoolSize);

    while (matingPool.size() < matingPoolSize) {
      matingPool.add(selectionOperator.execute(solutionList));
    }

    return matingPool;
  }
}