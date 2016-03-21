uniform vec2 lightLocation;
uniform vec3 lightColor;
uniform float screenHeight;

out vec4 color;
void main()
{
    float distance = length(lightLocation - gl_FragCoord.xy);
    float attenuation = 1.0 / distance;
    color = vec4(attenuation, attenuation, attenuation, pow(attenuation, 3)) * vec4(lightColor, 1);
	gl_Position = gl_ProjectionMatrix * gl_ModelViewMatrix * gl_Vertex;

}