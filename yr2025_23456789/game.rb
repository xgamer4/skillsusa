# frozen_string_literal: true

class Game
  attr_accessor :team1, :team2, :result

  def initialize(team1, team2)
    @team1 = team1
    @team2 = team2
    @result = game_result
  end

  def game
    loser = result == @team1 ? @team2 : @team1
    "#{@result.name} beats #{loser.name}"
  end

  private
  def game_result
    t1 = 0
    t1 += 1 if subcontest(@team1.dps.dps_proficiency, @team2.tank.tank_proficiency)
    t1 += 1 if subcontest(@team1.tank.tank_proficiency, @team2.dps.dps_proficiency)
    t1 += 1 if subcontest(@team1.support.support_proficiency, @team2.support.support_proficiency)
    if t1 >= 2
      return @team1
    else
      return @team2
    end
  end

  def subcontest(t1, t2)
    score = normalize(t1, t2)
    rand(0.0...1.0) <= score
  end

  def normalize(a, b)
    a / (b + a)
  end
end

