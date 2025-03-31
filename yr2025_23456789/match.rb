# frozen_string_literal: true

require './game'

class Match
  attr_reader :team1, :team2, :winner
  def initialize(team1, team2)
    @team1 = team1
    @team2 = team2
    @games = Array.new
    @winner = match_results

  end

  def prediction(team)
    @prediction = team
  end

  def make_prediction
    while @prediction == nil
      puts "type '1' if you think #{team1.name} will win"
      puts "type '2' if you think #{team2.name} will win"
      input = gets.chomp
      if input == "1"
        @prediction = team1
      elsif input == "2"
        @prediction = team2
      else
        puts "invalid input"
      end
    end
  end

  def view_match
    "#{team1.name} vs #{team2.name}"
  end

  def match_results
    game1 = Game.new(@team1, @team2)
    @games.push(game1)
    game2 = Game.new(@team1, @team2)
    @games.push(game2)
    return game1.result if game1.result == game2.result

    game3 = Game.new(@team1, @team2)
    @games.push(game3)
    game3.result
  end
  
  def match
    out = ''
    @games.each do |game|
      out += "#{game.game}\n"
    end
    out += "#{@winner.name} wins the match.\n"
    out += "You win the prediction!\n" if @prediction == @winner
    out += "You lose the prediction.\n" if @prediction != @winner
    out
  end
end
