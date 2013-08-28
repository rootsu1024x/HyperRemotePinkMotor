#!ruby
#シェバング行は適宜変更のこと

require "cgi"
cgi = CGI.new

print "Content-Type: text/plain\n"
print "\n"
print "NYAN"

   
if cgi.has_key?('power') then
	print cgi['power']
	Net::HTTP.get_print 'h2cinputableyourpc.example.com:8080', '/' + power
end
