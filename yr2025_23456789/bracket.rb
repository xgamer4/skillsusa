# frozen_string_literal: true

require './match'

class Bracket
  attr_reader :teams, :match1, :match2, :final

  def initialize(teams)
    @teams = teams
    @match1 = Match.new(@teams[0], @teams[1])
    @match2 = Match.new(@teams[2], @teams[3])
    @final = Match.new(@match1.winner, @match2.winner)
  end


end
