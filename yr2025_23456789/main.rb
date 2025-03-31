# frozen_string_literal: true

require './team'
require './bracket'

fuel = Team.new('Fuel')
outlaws = Team.new('Outlaws')
shock = Team.new('Shock')
dynasty = Team.new('Dynasty')

bracket = Bracket.new([fuel, outlaws, shock, dynasty])

puts fuel.team
puts outlaws.team
puts shock.team
puts dynasty.team

puts "\n\n\n"

puts bracket.match1.view_match
bracket.match1.make_prediction
puts bracket.match1.match
puts bracket.match2.view_match
bracket.match2.make_prediction
puts bracket.match2.match
puts bracket.final.view_match
bracket.final.make_prediction
puts bracket.final.match